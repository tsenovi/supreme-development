package ERP.parse;

public interface ControllerIO {
    @SuppressWarnings("unchecked")
    <T> T loadFile(String fileName);

    <T> void overwriteFile(String fileName, T data);
}
