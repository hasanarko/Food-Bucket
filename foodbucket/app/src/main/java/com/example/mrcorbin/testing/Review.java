package com.example.mrcorbin.testing;

public class Review {

  private int reviewId;
  private String reviewerName;
  private String review;
  private double ratingNumber;

  public Review(){}

    public Review(int reviewId, String reviewerName, String review, double ratingNumber) {
        this.reviewId = reviewId;
        this.reviewerName = reviewerName;
        this.review = review;
        this.ratingNumber = ratingNumber;
    }

    public int getReviewId() {
        return reviewId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public String getReview() {
        return review;
    }

    public double getRatingNumber() {
        return ratingNumber;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setRatingNumber(double ratingNumber) {
        this.ratingNumber = ratingNumber;
    }
}
