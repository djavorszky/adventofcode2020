package com.djavorszky.adventofcode.helper.file;

import com.djavorszky.adventofcode.helper.InputSource;
import org.apache.commons.io.IOUtils;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

public class FileInputSource implements InputSource {

    private final List<String> input;

    public FileInputSource(String filePath) throws Exception {
        input = IOUtils.readLines(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(filePath)), Charset.defaultCharset());
    }

    @Override
    public List<String> getInput() {
        return input;
    }
}
