package GUI_source;

/**
 * Created by Cedric on 11.05.2017.
 */
public class Current_Settings {
    private int style=0;

    public Current_Settings(int style) {
        this.style = style;
    }

    public int getStyle() {
        return style;
    }
    public String getStyleName(){
        String CSS;
        switch (this.getStyle()){
            case 0: CSS="style.css"; break;
            case 1: CSS="dark-style.css"; break;
            case 2: CSS="dark_inverted-style.css"; break;
            default:
                System.out.println("Something went wrong! Setting style to default (style.css)");
                CSS="style.css";
                break;
        }
        return CSS;
    }
    public void setAll(int style){
        this.style=style;
    }
}
