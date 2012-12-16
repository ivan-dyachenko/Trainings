package org.approvaltests.reporters.macosx;

import com.spun.util.ArrayUtils;
import com.spun.util.io.FileUtils;
import org.approvaltests.reporters.GenericDiffReporter;

import java.text.MessageFormat;
import java.util.List;

public class KSDiffReporter extends GenericDiffReporter {
    private static final String DIFF_PROGRAM = "/Applications/Kaleidoscope.app/Contents/MacOS/ksdiff";
    static final String MESSAGE = MessageFormat.format("Unable to find Kaleidoscope at {0}", DIFF_PROGRAM);
    private static List<String> fileTypes = ArrayUtils.combine(GenericDiffReporter.IMAGE_FILE_EXTENSIONS, GenericDiffReporter.TEXT_FILE_EXTENSIONS);
    public static final KSDiffReporter INSTANCE = new KSDiffReporter();

    public KSDiffReporter() {
        super(DIFF_PROGRAM, "%s %s", MESSAGE, fileTypes);
    }

    @Override
    public String getCommandLine(String received, String approved) {
        String command = "%s " + arguments;
        command = String.format(command, diffProgram, '"' + received + '"', '"' + approved + '"');
        System.out.println(command);
        return command;
    }

    @Override
    public void report(String received, String approved) throws Exception {
        if (!isWorkingInThisEnvironment(received)) {
            throw new RuntimeException(diffProgramNotFoundMessage);
        }
        FileUtils.createIfNeeded(approved);
        Process p = Runtime.getRuntime().exec(
                new String[]{"/bin/bash", "-c", getCommandLine(received, approved)});

    }
}
