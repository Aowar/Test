package org.leaarn_school.app.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

@Data
@Getter
@Setter
public class ServiceEntity {
    private int id;
    private String title;
    private double cost;
    private int duration;
    private String desc;
    private int discount;
    private Date date;
    private String imagePath;

    private ImageIcon image;

    public ServiceEntity(int id, String title, double cost, int duration, String desc, int discount, Date date, String imagePath) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.duration = duration;
        this.desc = desc;
        this.discount = discount;
        this.date = date;
        this.imagePath = imagePath.replaceAll(Pattern.quote("\\"), "/");
        this.date = date;

        try {
            this.image = new ImageIcon(ImageIO.read(ServiceEntity.class.getClassLoader().getResource(imagePath)).getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServiceEntity(String title, double cost, int duration, String desc, int discount, Date date, String imagePath) {
        this(-1, title, cost, duration, desc, discount, date, imagePath);
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public double getCost() {
        return this.cost;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getDiscount() {
        return this.discount;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ServiceEntity)) return false;
        final ServiceEntity other = (ServiceEntity) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        if (Double.compare(this.getCost(), other.getCost()) != 0) return false;
        if (this.getDuration() != other.getDuration()) return false;
        final Object this$desc = this.getDesc();
        final Object other$desc = other.getDesc();
        if (this$desc == null ? other$desc != null : !this$desc.equals(other$desc)) return false;
        if (this.getDiscount() != other.getDiscount()) return false;
        final Object this$imagePath = this.getImagePath();
        final Object other$imagePath = other.getImagePath();
        if (this$imagePath == null ? other$imagePath != null : !this$imagePath.equals(other$imagePath)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ServiceEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final long $cost = Double.doubleToLongBits(this.getCost());
        result = result * PRIME + (int) ($cost >>> 32 ^ $cost);
        result = result * PRIME + this.getDuration();
        final Object $desc = this.getDesc();
        result = result * PRIME + ($desc == null ? 43 : $desc.hashCode());
        result = result * PRIME + this.getDiscount();
        final Object $imagePath = this.getImagePath();
        result = result * PRIME + ($imagePath == null ? 43 : $imagePath.hashCode());
        return result;
    }

    public String toString() {
        return "ServiceEntity(id=" + this.getId() + ", title=" + this.getTitle() + ", cost=" + this.getCost() + ", duration=" + this.getDuration() + ", desc=" + this.getDesc() + ", discount=" + this.getDiscount() + ", imagePath=" + this.getImagePath() + ")";
    }
}
