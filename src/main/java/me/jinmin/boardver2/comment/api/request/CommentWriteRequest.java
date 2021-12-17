package me.jinmin.boardver2.comment.api.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentWriteRequest {
    private String content;
}
