package com.djavorszky.adventofcode.helper.str;

import com.djavorszky.adventofcode.helper.InputSource;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class StringInputSource implements InputSource {

    private final List<String> input;

    @Override
    public List<String> getInput() {
        return input;
    }
}
