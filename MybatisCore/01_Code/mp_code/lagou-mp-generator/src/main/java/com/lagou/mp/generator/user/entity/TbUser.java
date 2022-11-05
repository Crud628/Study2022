package com.lagou.mp.generator.user.entity;

    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author zimu
* @since 2020-10-30
*/
    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class TbUser implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 姓名
            */
    private String name;

            /**
            * 年龄
            */
    private Integer age;

            /**
            * 邮箱
            */
    private String email;

    private Integer version;

    private Integer deleted;


}
