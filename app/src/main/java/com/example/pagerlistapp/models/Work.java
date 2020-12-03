package com.example.pagerlistapp.models;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
public class Work implements Serializable {

    @NonNull
    @PrimaryKey
    private Integer work_id;
    private String user_id;
    private String uri_owner;
    private Integer media_id;
    private int date_upload;
    private int type_id;
    private String masterwork;
    private String dt_finish;
    private int sx;
    private int sy;
    private int sz;
    @Ignore
    private Coords coords;
    private String is_adult;
    private String privacy;
    private String name;
    private String artist_hidden_price;
    private int description_length;
    @Ignore
    private List<String> names;
    private String description;
    private String uri;
   // private List<Tag> tags;
   @Ignore
    private List<Integer> alt_media_ids;
    @Ignore
    private List<Integer> style_ids;
    @Ignore
    private List<Integer> genre_ids;
    @Embedded
    private Counters counters;
    private Integer collection_id;
    @Ignore
    private Boolean isAnimated = false;

    public void setIsAnimated(){
        isAnimated = true;
    }

    public Boolean getIsAnimated(){
        return this.isAnimated;
    }

    public Integer getWork_id() {
        return work_id;
    }

    public void setWork_id(Integer work_id) {
        this.work_id = work_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUri_owner(String uri_owner) {
        this.uri_owner = uri_owner;
    }

    public void setMedia_id(Integer media_id) {
        this.media_id = media_id;
    }

    public void setDate_upload(int date_upload) {
        this.date_upload = date_upload;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public void setMasterwork(String masterwork) {
        this.masterwork = masterwork;
    }

    public void setDt_finish(String dt_finish) {
        this.dt_finish = dt_finish;
    }

    public void setSx(int sx) {
        this.sx = sx;
    }

    public void setSy(int sy) {
        this.sy = sy;
    }

    public void setSz(int sz) {
        this.sz = sz;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public void setIs_adult(String is_adult) {
        this.is_adult = is_adult;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist_hidden_price(String artist_hidden_price) {
        this.artist_hidden_price = artist_hidden_price;
    }

    public void setDescription_length(int description_length) {
        this.description_length = description_length;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setAlt_media_ids(List<Integer> alt_media_ids) {
        this.alt_media_ids = alt_media_ids;
    }

    public void setStyle_ids(List<Integer> style_ids) {
        this.style_ids = style_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public void setCounters(Counters counters) {
        this.counters = counters;
    }

    public void setCollection_id(Integer collection_id) {
        this.collection_id = collection_id;
    }

    public void setAset_ids(List<Integer> aset_ids) {
        this.aset_ids = aset_ids;
    }

    public void setInfos(Infos infos) {
        this.infos = infos;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }

    public void setMaterial_ids(List<Integer> material_ids) {
        this.material_ids = material_ids;
    }

    public void setTechnique_ids(List<Integer> technique_ids) {
        this.technique_ids = technique_ids;
    }

    public void setArtist_ids(List<Integer> artist_ids) {
        this.artist_ids = artist_ids;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescription_html(String description_html) {
        this.description_html = description_html;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public void setTechniques(List<Technique> techniques) {
        this.techniques = techniques;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUri_owner() {
        return uri_owner;
    }

    public Integer getMedia_id() {
        return media_id;
    }

    public int getDate_upload() {
        return date_upload;
    }

    public int getType_id() {
        return type_id;
    }

    public String getMasterwork() {
        return masterwork;
    }

    public String getDt_finish() {
        return dt_finish;
    }

    public int getSx() {
        return sx;
    }

    public int getSy() {
        return sy;
    }

    public int getSz() {
        return sz;
    }

    public Coords getCoords() {
        return coords;
    }

    public String getIs_adult() {
        return is_adult;
    }

    public String getPrivacy() {
        return privacy;
    }

    public String getName() {
        return name;
    }

    public String getArtist_hidden_price() {
        return artist_hidden_price;
    }

    public int getDescription_length() {
        return description_length;
    }

    public List<String> getNames() {
        return names;
    }

    public String getDescription() {
        return description;
    }

    public String getUri() {
        return uri;
    }


    public List<Integer> getAlt_media_ids() {
        return alt_media_ids;
    }

    public List<Integer> getStyle_ids() {
        return style_ids;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public Counters getCounters() {
        return counters;
    }

    public Integer getCollection_id() {
        return collection_id;
    }

    public List<Integer> getAset_ids() {
        return aset_ids;
    }

    public Infos getInfos() {
        return infos;
    }

    public Flags getFlags() {
        return flags;
    }

    public Colors getColors() {
        return colors;
    }

    public List<Integer> getMaterial_ids() {
        return material_ids;
    }

    public List<Integer> getTechnique_ids() {
        return technique_ids;
    }

    public List<Integer> getArtist_ids() {
        return artist_ids;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescription_html() {
        return description_html;
    }

    public Media getMedia() {
        return media;
    }

    public List<Technique> getTechniques() {
        return techniques;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public List<Set> getSets() {
        return sets;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    @Ignore
    private List<Integer> aset_ids;
    @Ignore
    private Infos infos;
    @Ignore
    private Flags flags;
    @Embedded
    private Colors colors;
    @Ignore
    private List <Integer> material_ids;
    @Ignore
    private List <Integer> technique_ids;
    @Ignore
    private List <Integer> artist_ids;
    @Ignore
    private Status status;
    private String description_html;
    @Embedded
    private Media media;
    @Ignore
    private List<Technique> techniques;
    @Ignore
    private List<Style> styles;
    @Ignore
    private List<Material> materials;
    @Ignore
    private List<Set> sets;
    @Ignore
    private List<Genre> genres;

    public Boolean getAnimated() {
        return isAnimated;
    }

    public void setAnimated(Boolean animated) {
        isAnimated = animated;
    }
}






