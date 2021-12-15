package me.jinmin.boardver2.board.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jinmin.boardver2.board.model.BoardCategory;

@Getter
@NoArgsConstructor
public class BoardUpdateRequest {

    private String title;
    private String content;
    private BoardCategory category;

}
