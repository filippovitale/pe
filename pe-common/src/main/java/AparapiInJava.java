
import java.util.List;

import com.amd.aparapi.Kernel;
import com.amd.aparapi.device.Device;
import com.amd.aparapi.device.OpenCLDevice;
import com.amd.aparapi.internal.opencl.OpenCLPlatform;

public class AparapiInJava {
    public static void main(String[] _args) {
        System.out.println("com.amd.aparapi.sample.info.Main");
        List<OpenCLPlatform> platforms = (new OpenCLPlatform()).getOpenCLPlatforms();
        System.out.println("Machine contains " + platforms.size() + " OpenCL platforms");


        final int data[] = new int[1024];
        Kernel kernel = new Kernel() {
            @Override
            public void run() {
                data[getGlobalId()] = getGlobalId();
            }
        };
        kernel.execute(data.length);

        if (kernel.getExecutionMode().equals(Kernel.EXECUTION_MODE.GPU))
            System.out.println("ExecutionMode=" + kernel.getExecutionMode());

        System.out.println(data[24]);
    }
}
