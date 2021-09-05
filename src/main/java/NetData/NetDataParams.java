package NetData;

import java.util.StringJoiner;

public class NetDataParams {

    private String chart;
    private String points;
    private String before;
    private String after;
    private String group;
    private String gtime;
    private String options;
    private String dimensions;


    public NetDataParams(String chart){
        this.chart = chart;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getPoints() {
        return points;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getBefore() {
        return before;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getAfter() {
        return after;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getDimensions() {
        return dimensions;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGtime() {
        return gtime;
    }

    public void setGtime(String gtime) {
        this.gtime = gtime;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getQuery(){
        StringJoiner query = new StringJoiner("&");
        query.add("chart=" + chart);
        if(options != null){
            query.add("options=" + options);
        }
        if(gtime != null){
            query.add("options=" + gtime);
        }
        if(group != null){
            query.add("group="+ group);
        }
        if(dimensions != null){
            query.add("dimensions=" + dimensions);
        }
        if(after != null){
            query.add("afer=" + after);
        }
        if(before!=null){
            query.add("before=" + before);
        }
        if(points != null){
            query.add("points=" + points);
        }
        return query.toString();
    }
}


