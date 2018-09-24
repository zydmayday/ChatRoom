package com.summer.model;

/**
 * Created by jacky on 07/04/2017.
 */
public class Todo {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static final class Builder {
        private String text;

        private Builder() {
        }

        public static Builder aTodo() {
            return new Builder();
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Todo build() {
            Todo todo = new Todo();
            todo.setText(text);
            return todo;
        }
    }
}
