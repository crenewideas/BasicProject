package cn.pxl.config.confingbean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "pattern")
public class Pattern {
    private String dateFormat;
    private String envSharedValue;
}
