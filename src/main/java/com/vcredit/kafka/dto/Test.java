package com.vcredit.kafka.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/8/17 17:33
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Test implements Serializable {
    private static final long serialVersionUID = 7565823248148436403L;
    private String msg;
}
