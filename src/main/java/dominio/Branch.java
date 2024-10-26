package dominio;

import java.util.Objects;

public class Branch {
    private String code;
    private String name;

    public Branch(String code) {
        this.code = code;
    }

    public Branch(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(name, branch.name);
    }

    @Override
    public String toString() {
        return "Branch[" + code + ", " + name + ']';
    }
}
