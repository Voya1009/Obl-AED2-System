package dominio;

import java.util.Objects;

public class Branch implements Comparable<Branch> {
    private String code;
    private String name;

    public Branch(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Branch(String code) { this.code = code; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public int compareTo(Branch b) { return code.compareTo(b.code); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(code, branch.code);
    }

    @Override
    public String toString() {
        return code + ";" + name;
    }
}
