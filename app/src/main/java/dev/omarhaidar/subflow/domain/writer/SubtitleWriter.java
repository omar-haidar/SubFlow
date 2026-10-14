package dev.omarhaidar.subflow.domain.writer;

import dev.omarhaidar.subflow.domain.model.ConversionOptions;
import dev.omarhaidar.subflow.domain.model.SubtitleCue;

import java.util.List;

public interface SubtitleWriter {
    String write(List<SubtitleCue> cues, ConversionOptions options);
}
