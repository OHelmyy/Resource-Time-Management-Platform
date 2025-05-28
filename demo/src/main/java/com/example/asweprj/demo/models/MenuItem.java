package com.example.asweprj.demo.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;
    private Integer sortOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private MenuItem parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("sortOrder ASC")
    private List<MenuItem> subItems;

    public MenuItem() {}

    public MenuItem(Long id, String name, String url, Integer sortOrder, MenuItem parent, List<MenuItem> subItems) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.sortOrder = sortOrder;
        this.parent = parent;
        this.subItems = subItems;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public MenuItem getParent() {
        return parent;
    }

    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

    public List<MenuItem> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<MenuItem> subItems) {
        this.subItems = subItems;
    }

    // Builder-style methods (optional)

    public MenuItem id(Long id) {
        this.id = id;
        return this;
    }

    public MenuItem name(String name) {
        this.name = name;
        return this;
    }

    public MenuItem url(String url) {
        this.url = url;
        return this;
    }

    public MenuItem sortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public MenuItem parent(MenuItem parent) {
        this.parent = parent;
        return this;
    }

    public MenuItem subItems(List<MenuItem> subItems) {
        this.subItems = subItems;
        return this;
    }

    // equals, hashCode, and toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem that = (MenuItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(sortOrder, that.sortOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, sortOrder);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", sortOrder=" + sortOrder +
                ", parentId=" + (parent != null ? parent.getId() : null) +
                '}';
    }
}
