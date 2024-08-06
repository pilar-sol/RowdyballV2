package edu.utsa.cs3443.rowdyballv2.model;

/**
 * The Response class represents a response in the RowdyBall application.
 * It contains the text of the response.
 */
public class Response {
    private String text;
    /**
     * Constructs a new Response with the specified text.
     * @param text the text of the response
     */
    public Response(String text) {
        this.text = text;
    }
    /**
     * Returns the text of the response.
     * @return the text of the response
     */
    public String getText() {
        return text;
    }
    /**
     * Sets the text of the response.
     * @param text the new text of the response
     */
    public void setText(String text) {
        this.text = text;
    }
}
