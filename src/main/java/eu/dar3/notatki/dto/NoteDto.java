package eu.dar3.notatki.dto;

public class NoteDto {
    private Long id;
    private String title;
    private String content;

    private NoteDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public NoteDto() {
    }

    public static NoteDto of(Long id, String title, String content) {
        return new NoteDto(id, title, content);
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
