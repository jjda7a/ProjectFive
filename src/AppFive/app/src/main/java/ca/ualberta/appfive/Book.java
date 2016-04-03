package ca.ualberta.appfive;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Class for the book object.
 * Contains data for a book, status and history
 *
 * Model
 */
public class Book {
    /**
     * enum for different status of the book
     */
    public enum Status {AVAILABLE, BIDDED, BORROWED}

    /**
     * This String id will be generated by elastic search.
     * If id is null, send to database to get an id
     */
    private String id = null;
    private String title;
    private String description;
    private String genre;
    private Status status;
    private OwnerInfo owner;
    private String author;
    // TODO: Thumbnail is a picture
    private transient Bitmap thumbnail;
    private String thumbnailBase64;
    // TODO: Make bids a Class
    private ArrayList<Bid> bids = new ArrayList<Bid>();

    /*
    public Book() {
        super();
    }
    */



    /**
     * Construct a book from scratch, without an id.
     * @param title Book title
     * @param description Book description
     * @param genre Book genre type
     * @param thumbnail A picture of the cover
     */
    public Book(String title,String author, String description, String genre, Bitmap thumbnail) {
        super();

        this.title = title;
        this.author = author;
        this.description = description;
        this.genre = genre;
        this.status = Status.AVAILABLE;
        this.owner = new OwnerInfo();
        setThumbnail(thumbnail);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public OwnerInfo getOwner() {
        return owner;
    }

    public void setOwner(OwnerInfo owner) {
        this.owner = owner;
    }

    public ArrayList<Bid> getBids() {
        return bids;
    }

    public Bid getBid(int index) {
        return bids.get(index);
    }

    public void addBid(Bid bid){
        this.bids.add(bid);
    }

    public void deleteBid (Bid bid){
        this.bids.remove(bid);
    }

    public void deleteBids (){
        this.bids.clear();
    }

    public void setThumbnail(Bitmap newThumbnail) {
        if(newThumbnail != null){
            thumbnail = newThumbnail;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            newThumbnail.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);

            byte[] b = byteArrayOutputStream.toByteArray();
            thumbnailBase64 = Base64.encodeToString(b, Base64.DEFAULT);
            thumbnail = null;
        }
    }
    public void deleteThumbnail() {
        thumbnail = null;
        thumbnailBase64 = null;

    }
    public Bitmap getThumbnail() {
        if (thumbnail == null && thumbnailBase64 != null){
            byte[] decodeString = Base64.decode(thumbnailBase64, Base64.DEFAULT);
            thumbnail = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        }
        return thumbnail;
    }

    public void updateStatus(){
        if (this.getBids().isEmpty()){
            this.setStatus(Status.AVAILABLE);
        }else{
            this.setStatus(Status.BIDDED);
        }
    }

}
