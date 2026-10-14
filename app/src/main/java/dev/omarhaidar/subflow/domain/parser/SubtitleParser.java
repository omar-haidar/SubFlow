package dev.omarhaidar.subflow.domain.parser;

import dev.omarhaidar.subflow.domain.model.SubtitleCue;

import java.util.List;

public interface SubtitleParser {
    List<SubtitleCue> parse(String content) throws SubtitleParseException;
}
