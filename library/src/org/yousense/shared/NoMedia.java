package org.yousense.shared;

import android.content.Context;
import org.apache.commons.io.FileUtils;
import org.yousense.common.Files;
import org.yousense.eventlog.DebugLog;
import org.yousense.eventlog.EventLog;
import org.yousense.upload.UploadService;

import java.io.File;
import java.io.IOException;

/**
 * Creates .nomedia files in all YouSense data directories, to avoid MediaServer scanning those.
 */
public class NoMedia {

    public static void initDirectories(Context context) {
        deleteOldDirectories(context);
        createNoMediaFiles(context);
    }

    public static void createNoMediaFiles(Context context) {
        try {
            // Current directories
            createNoMediaFile(EventLog.getLogDirectory(context));  // internal "yousense-eventlog"
            createNoMediaFile(UploadService.getUploadDirectory(context));  // internal "yousense-upload"
        } catch (IOException e) {
            DebugLog.e(Config.TAG, "Error creating .nomedia files", e);
        }
    }

    public static void deleteOldDirectories(Context context) {
        // These directories are no longer used, and should not exist.
        try {
            deleteDir(context.getExternalFilesDir(null));
            deleteDir(Files.getInternalSubdir(context, "yousense-eventlog-open"));
        } catch (IOException e) {
            DebugLog.e(Config.TAG, "Error deleting old directories", e);
        }
    }

    public static void createNoMediaFile(File dir) throws IOException {
        File file = new File(dir, ".nomedia");
        DebugLog.i(Config.TAG, "Creating " + file.getAbsolutePath());
        FileUtils.write(file, "");
    }

    public static void deleteDir(File dir) {
        DebugLog.i(Config.TAG, "Deleting " + dir.getAbsolutePath());
        if (!FileUtils.deleteQuietly(dir)) {
            DebugLog.e(Config.TAG, "Failed to delete " + dir.getAbsolutePath());
        }
    }
}
