package json;

import static json.JsonUtils.toJson;

public abstract class BeanToJson <T>{

    public abstract T getObj();

    @Override
    public String toString() {
        return toJson(getObj());
    }
}