package ca.ualberta.appfive;

import java.util.ArrayList;

/**
 * Class for the book object.
 * Contains data for a book, status and history
 *
 * Model
 */
public class Book extends BModel<BView>{
    /**
     * Kinds of status that the book can have
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
    // TODO: Thumbnail is a picture
    private String thumbnail = "thumbnail";
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
    public Book(String title, String description, String genre, String thumbnail) {
        super();
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.status = Status.AVAILABLE;
        this.owner = new OwnerInfo();
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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

    public void updateStatus(){
        if (this.getBids().isEmpty()){
            this.setStatus(Status.AVAILABLE);
        }else{
            this.setStatus(Status.BIDDED);
        }
    }

}
