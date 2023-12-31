package com.futurecreator.dao.pojo.module.R;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterfaceR {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Integer amount;
}
