package com.xumpy.vastgoed.db;

import javax.persistence.*;

@Entity
@Table(name="TELEGRAM")
public class TelegramDBPojo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) @Column(name="PK_ID") Integer pkId;
    @Column(name="CHAT_ID") private Long chatId;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
}
