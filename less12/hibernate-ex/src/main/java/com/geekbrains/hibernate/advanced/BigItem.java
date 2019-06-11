package com.geekbrains.hibernate.advanced;

import org.hibernate.annotations.OptimisticLock;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "big_items")
public class BigItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "val")
    int val;

    @Column(name = "junkField")
    @OptimisticLock(excluded = true)
    int junkField;

    @Version
    long version;

    public void setVal(int val) {
        this.val = val;
    }

    public BigItem() {
    }

    public BigItem(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.format("BigItem [id = %d, val = %d, version = %d]", id, val, version);
    }
}
