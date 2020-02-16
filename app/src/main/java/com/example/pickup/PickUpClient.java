package com.example.pickup;

import java.io.*;
import java.net.*;
import java.util.*;

import android.provider.Settings;

public class PickUpClient {

    public double latitude;
    public double longitude;

    public static class LobbyEntry implements Comparable<LobbyEntry> {

        public boolean isHost;
        public String username;
        public double dist;

        LobbyEntry(boolean isHost, String username, double dist) {
            this.isHost = isHost;
            this.username = username;
            this.dist = dist;
        }

        public int compareTo(LobbyEntry o) {
            if (isHost ^ o.isHost) {
                return isHost ? 1 : -1;
            }
            if (dist < o.dist) {
                return -1;
            }
            if (dist > o.dist) {
                return 1;
            }
            return username.compareTo(o.username);
        }

    }

    public ArrayList<LobbyEntry> players;

    public static final String ANDROID_ID = Settings.Secure.ANDROID_ID;

    private ArrayList<String> send(String prefix, String request) throws Throwable {
        Socket sock = new Socket();
        try {
            sock.bind(new InetSocketAddress("hostname", 12345));
            OutputStreamWriter os = new OutputStreamWriter(sock.getOutputStream());
            os.write(ANDROID_ID + "\n" + prefix);
            os.write("" + request.length());
            os.write(request);
            BufferedReader is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            ArrayList<String> lines = new ArrayList<String>();
            String line;
            while ((line = is.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (Throwable e) {
            throw e;
        } finally {
            sock.close();
        }
    }

    public void createGeofence(int radius, int capacity, String desc) throws Throwable {
        ArrayList<String> lines = send("c", radius + "\n" + capacity + "\n" + desc);
        if (lines.size() == 0) {
            throw new Exception("Heard no reply from server");
        }
        String msg = lines.get(0);
        if (msg.charAt(0) != 255) {
            throw new Exception(msg);
        }
    }

    public void destroyGeofence() throws Throwable {
        ArrayList<String> lines = send("d", "");
        if (lines.size() == 0) {
            throw new Exception("Heard no reply from server");
        }
        String msg = lines.get(0);
        if (msg.charAt(0) != 255) {
            throw new Exception(msg);
        }
    }

    private int readUserStats(ArrayList<String> lines, int index, ArrayList<LobbyEntry> out) throws Throwable {
        if (lines.size() - index < 2) {
            throw new IOException("Server response EOF early");
        }
        String host = lines.get(index);
        double hdist;
        try {
            hdist = Double.parseDouble(lines.get(2));
        } catch (Throwable e) {
            throw new Exception("Invalid response from server: Cannot parse distance as double");
        }
        out.add(new LobbyEntry(true, host, hdist));
        int i = index + 2;
        for (; i < lines.size(); i += 2) {
            String name = lines.get(i);
            if (name.charAt(0) == 254) {
                break;
            }
            double dist;
            try {
                dist = Double.parseDouble(lines.get(i + 1));
            } catch (Throwable e) {
                throw new Exception("Invalid response from server: Cannot parse distance as double");
            }
            out.add(new LobbyEntry(false, name, dist));
        }
        Collections.sort(players);
        return i;
    }

    public void update() throws Throwable {
        ArrayList<String> lines = send("u", "");
        if (lines.size() == 0) {
            throw new Exception("Heard no reply from server");
        }
        String[] latlong = lines.get(0).split(",");
        try {
            latitude = Double.parseDouble(latlong[0]);
            longitude = Double.parseDouble(latlong[1]);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid response from server: Failed to parse latlong");
        }
        players.clear();
        if (lines.size() > 1) {
            readUserStats(lines, 1, players);
        }
    }

    public static class EventEntry {

        public String id;
        public double latitude;
        public double longitude;
        public String desc;
        public ArrayList<LobbyEntry> players;

    }

    public ArrayList<EventEntry> searchEvents(int radius) throws Throwable {
        ArrayList<String> lines = send("s", "" + radius);
        ArrayList<EventEntry> toreturn = new ArrayList<EventEntry>();
        for (int i = 0; i < lines.size(); i++) {
            if (i + 3 >= lines.size()) {
                if (!lines.get(i).equals("" + (char)255)) {
                    throw new Exception("Non-OK response from server or invalid format");
                }
            }
            String geoid = lines.get(i++);
            String latlongS = lines.get(i++);
            String[] latlong = latlongS.split(",");
            double latt;
            double longi;
            try {
                latt = Double.parseDouble(latlong[0]);
                longi = Double.parseDouble(latlong[1]);
            } catch (NumberFormatException e) {
                throw new Exception("Invalid response from server: Failed to parse latlong");
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while (i < lines.size() && !(line = lines.get(i++)).endsWith("" + (char)254)) {
                sb.append(line + '\n');
            }
            if (i == lines.size()) {
                throw new Exception("Server response EOF early");
            }
            ArrayList<LobbyEntry> lobby = new ArrayList<LobbyEntry>();
            i = readUserStats(lines, i, lobby);
            EventEntry entry = new EventEntry();
            entry.id = geoid;
            entry.latitude = latt;
            entry.longitude = longi;
            entry.desc = sb.toString();
            entry.players = lobby;
            toreturn.add(entry);
        }
        return toreturn;
    }

    public void joinEvent(String geoid) throws Throwable {
        ArrayList<String> lines = send("j", geoid);
        if (lines.size() == 0) {
            throw new Exception("Heard no reply from server");
        }
        String msg = lines.get(0);
        if (msg.charAt(0) != 255) {
            throw new Exception(msg);
        }
    }

    public void leaveEvent(String geoid) throws Throwable {
        ArrayList<String> lines = send("l", geoid);
        if (lines.size() == 0) {
            throw new Exception("Heard no reply from server");
        }
        String msg = lines.get(0);
        if (msg.charAt(0) != 255) {
            throw new Exception(msg);
        }
    }

    public void nameSet(String username) throws Throwable {
        ArrayList<String> lines = send("n", username);
        if (lines.size() == 0) {
            throw new Exception("Heard no reply from server");
        }
        String msg = lines.get(0);
        if (msg.charAt(0) != 255) {
            throw new Exception(msg);
        }
    }

}
