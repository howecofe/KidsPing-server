package com.kidsworld.kidsping.domain.book.dto.response;

import com.kidsworld.kidsping.domain.book.entity.Book;
import com.kidsworld.kidsping.domain.book.entity.enums.MbtiType;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BookResponse {
    private Long bookId;
    private Long genreId;
    private String title;
    private String summary;
    private String author;
    private String publisher;
    private Integer age;
    private String imageUrl;
    private MbtiType mbtiType;

    private Integer eScore;
    private Integer iScore;
    private Integer sScore;
    private Integer nScore;
    private Integer tScore;
    private Integer fScore;
    private Integer jScore;
    private Integer pScore;

    public static BookResponse from(Book book) {
        return BookResponse.builder()
                .bookId(book.getId())
                .genreId(book.getGenre().getId())
                .title(book.getTitle())
                .summary(book.getSummary())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .age(book.getAge())
                .imageUrl(book.getImageUrl())
                .mbtiType(book.getBookMbti().getBookMbtiType())
                .eScore(book.getBookMbti().getEScore())
                .iScore(book.getBookMbti().getIScore())
                .sScore(book.getBookMbti().getSScore())
                .nScore(book.getBookMbti().getNScore())
                .tScore(book.getBookMbti().getTScore())
                .fScore(book.getBookMbti().getFScore())
                .jScore(book.getBookMbti().getJScore())
                .pScore(book.getBookMbti().getPScore())
                .build();
    }
}
