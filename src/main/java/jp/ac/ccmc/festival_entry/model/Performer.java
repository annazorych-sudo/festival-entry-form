package jp.ac.ccmc.festival_entry.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "performers")
public class Performer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "氏名を入力してください。")
    @Size(max = 40, message = "氏名は40文字以内にしてください。")
    private String name;

    @NotBlank(message = "電話番号を入力してください。")
    @Pattern(regexp = "^[0-9\\-]+$", message = "電話番号は数字とハイフンのみ入力してください。")
    @Size(max = 20, message = "電話番号は20文字以内にしてください。")
    private String phone;

    @NotBlank(message = "演目のテーマを入力してください。")
    @Size(max = 80, message = "テーマは80文字以内にしてください。")
    private String title;

    @NotNull(message = "出演時間を入力してください。")
    @Min(value = 1, message = "出演時間は1分以上にしてください。")
    @Max(value = 120, message = "出演時間は120分以内にしてください。")
    private Integer minutes;

    @NotNull(message = "搬入補助の有無を選んでください。")
    private Boolean needHelp;
}
