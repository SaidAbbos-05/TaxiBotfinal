package org.example.services;

import org.example.model.Elon;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Driver_s_Service {

    private static String fromVil;
    private static String toVil;

    public static String getFromVil() {
        return fromVil;
    }

    public static void setFromVil(String fromVil) {
        Driver_s_Service.fromVil = fromVil;
    }

    public static String getToVil() {
        return toVil;
    }

    public static void setToVil(String toVil) {
        Driver_s_Service.toVil = toVil;
    }

    public List<Elon> showsExactElonlar(Long chatId) {
        String root = "C:\\Pdp package\\TAXIBOT\\src\\main\\java\\org\\example\\DB\\elonlar";
        File folder = new File(root);
        if(folder.isDirectory()){
            File[] txtFiles = folder.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".txt");
                }
            });
            if (txtFiles != null) {
                List<Elon> elonlar = new ArrayList<>();
                for (File file : txtFiles) {
                    if (file != null) {
                        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                            Elon elon = (Elon) inputStream.readObject();
                            if(elon.getBorishVil().equals(fromVil) && elon.getBorishVil().equals(toVil)) {
                                elonlar.add(elon);
                            }
                        } catch (IOException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                return elonlar;
            }
        }
        return null;
    }



    public List<Elon> showsAllElon(Long chatId) {
        String root = "C:\\Pdp package\\TAXIBOT\\src\\main\\java\\org\\example\\DB\\elonlar";
        File folder = new File(root);
        if (folder.isDirectory()) {
            File[] txtFiles = folder.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".txt");
                }
            });
            if (txtFiles != null) {
                List<Elon> elonlar = new ArrayList<>();
                for (File file : txtFiles) {
                    if (file != null) {
                        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                            Elon elon = (Elon) inputStream.readObject();
                            elonlar.add(elon);
                        } catch (IOException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                return elonlar;
            }
        }
        return null;
    }


}

