package app.model.entity.Exemplos;

import app.model.core.AbstractIdClass;
import app.model.exception.StringSizeException;

public class Book extends AbstractIdClass
{
    private String  title;
    private String  author;
    private String  publisher;
    private String  edition;
    private String  isbn10;
    private String  isbn13;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title) throws StringSizeException
    {
        if(title.length() > 255)
        {
            throw new StringSizeException();
        }
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author) throws StringSizeException
    {
        if(author.length() > 255)
        {
            throw new StringSizeException();
        }
        this.author = author;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)throws StringSizeException
    {
        if(publisher.length() > 255)
        {
            throw new StringSizeException();
        }
        this.publisher = publisher;
    }

    public String getEdition()
    {
        return edition;
    }

    public void setEdition(String edition)
    {
        this.edition = edition;
    }

    public String getIsbn10()
    {
        return isbn10;
    }

    public void setIsbn10(String isbn10)throws StringSizeException
    {
        if(isbn10.length() > 45)
        {
            throw new StringSizeException();
        }
        this.isbn10 = isbn10;
    }

    public String getIsbn13()
    {
        return isbn13;
    }

    public void setIsbn13(String isbn13)throws StringSizeException
    {
        if(isbn13.length() > 45)
        {
            throw new StringSizeException();
        }
        this.isbn13 = isbn13;
    }

    @Override
    public String getTableName() {
        return "tb_book";
    }
}
