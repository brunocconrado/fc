package br.com.conrado.fcontrol.commons.util;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class LogUtils {

    private static final Logger LOG = LoggerFactory.getLogger(LogUtils.class);

    public static Marker marker(String marker) {
        return MarkerFactory.getMarker(marker);
    }

    public static class DiskUsage {

        private static final int MIN_DISK_GB = Integer.getInteger("diskUsage.alert", (1024 * 4));
        private static final String MOUNT_POINT = "/";

        public static void printUsage() {
            if (!LOG.isInfoEnabled()) {
                return;
            }
            try {
                FileSystem fs = FileSystems.getDefault();
                for (FileStore store : fs.getFileStores()) {
                    if (store.name().startsWith(MOUNT_POINT)) {
                        printDiskUsage(store);
                    }
                }
            } catch (Exception e) {
                LOG.debug("Error invoking printUsage() ", e);
            }
        }

        public static long printDiskUsage(FileStore store) throws IOException {
            long avail = store.getUsableSpace() / 1024 / 1024;
            if (avail < MIN_DISK_GB) {
                LOG.info("Low disk usage {}", store.name());
            }
            return avail;
        }
    }

    public static class SystemPropertiesInitializer {

        public static void reset(boolean isDebug) {
            // print das mensagens do BRM
            System.setProperty(
                    "com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump",
                    System.getProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump",
                            Boolean.toString(isDebug)));
            // oracle dms
            System.setProperty("oracle.dms.console.DMSConsole", System.getProperty("oracle.dms.console.DMSConsole", ""));
            // ehcache
            System.setProperty("net.sf.ehcache.skipUpdateCheck", "true");
            // quartz
            System.setProperty("org.quartz.scheduler.skipUpdateCheck", "true");
            // debug
            print();
        }

        public static void print() {
            if (LOG.isTraceEnabled()) {
                Marker marker = marker("ENV");
                LOG.trace(marker, "system={}", System.getProperties());
            }
        }

    }

}
