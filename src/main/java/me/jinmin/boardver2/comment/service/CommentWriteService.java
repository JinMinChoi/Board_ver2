package me.jinmin.boardver2.comment.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jinmin.boardver2.board.model.Board;
import me.jinmin.boardver2.board.service.BoardFindService;
import me.jinmin.boardver2.comment.api.request.CommentWriteRequest;
import me.jinmin.boardver2.comment.model.Comment;
import me.jinmin.boardver2.comment.repository.CommentRepository;
import me.jinmin.boardver2.notification.FirebaseCloudMessageService;
import me.jinmin.boardver2.user.model.User;
import me.jinmin.boardver2.user.service.UserFindService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentWriteService {

    private final CommentRepository commentRepository;
    private final UserFindService userFindService;
    private final BoardFindService boardFindService;
    private final FirebaseCloudMessageService messageService;

    @Transactional
    public Long writeComment(Long userId, Long boardId, CommentWriteRequest commentWriteRequest) throws FirebaseMessagingException, IOException {
        Board board = boardFindService.findById(boardId);
        User user = userFindService.findById(userId);

        Comment comment = Comment.builder()
                .content(commentWriteRequest.getContent())
                .writer(user.getName())
                .board(board)
                .build();

        Comment savedComment = commentRepository.save(comment);
        user.writeComment(savedComment);

        String targetToken = board.getUser().getDeviceToken();
        sendMessageToBoardWriter(targetToken, "Comment Notification!", comment.getWriter(), comment.getContent());
        return savedComment.getComment_id();
    }

    private void sendMessageToBoardWriter(String targetToken, String title, String writer, String content) throws FirebaseMessagingException, IOException {
        messageService.sendMessageTo(targetToken, title, "[" + writer + "]" + "가 댓글 : <" + content + ">을 작성했습니다.");
    }
}
