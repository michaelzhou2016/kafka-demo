package com.vcredit.kafka.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/8/20 9:30
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Foo implements Serializable {
    private static final long serialVersionUID = -6257237213654214992L;
    private Integer id;
    private String msg;
}
