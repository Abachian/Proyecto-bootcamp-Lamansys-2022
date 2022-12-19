package ar.lamansys.sgx.shared.flavor;

public enum FlavorBo {

    ROOT("root");

    private final String text;

    FlavorBo(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

}
