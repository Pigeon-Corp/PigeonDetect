import org.tensorflow.Tensor;
import org.tensorflow.framework.TensorProto;
import org.tensorflow.framework.TensorShapeProto;

public class PigeonSpeciesDetector {

    // ... (other methods remain unchanged)

    public static void main(String[] args) throws IOException {
        String modelPath = "path/to/your/model.pb";
        String imagePath = "path/to/your/image.jpg";
        int height = 224; // Model specific input height
        int width = 224;  // Model specific input width

        GraphDef graphDef = loadGraphDef(modelPath);

        try (Graph graph = new Graph()) {
            graph.importGraphDef(graphDef.toByteArray());
            try (Session session = new Session(graph)) {
                float[] imgData = ImagePreprocessor.preprocessImage(imagePath, height, width);

                TensorProto inputTensor = TensorProto.newBuilder()
                        .setDtype(TensorFlow.DT_FLOAT)
                        .addTensors(Tensor.newBuilder()
                                .addAllFloatVal(FloatBuffer.wrap(imgData))
                                .setTensorShape(TensorShapeProto.newBuilder()
                                        .addDim(TensorShapeProto.Dim.newBuilder().setSize(1))
                                        .addDim(TensorShapeProto.Dim.newBuilder().setSize(height))
                                        .addDim(TensorShapeProto.Dim.newBuilder().setSize(width))
                                        .addDim(TensorShapeProto.Dim.newBuilder().setSize(3))
                                ))
                        .build();

                Tensor<?> result = session.runner()
                        .feed("input_tensor", inputTensor)
                        .fetch("output_tensor")
                        .run()
                        .get(0);

                float[][] output = new float[1][result.shape()[1]];
                result.copyTo(output);

                int speciesIndex = findMaxIndex(output[0]);
                System.out.println("Predicted species index: " + speciesIndex);
                // Map the index to species name using your own mapping
            }
        }
    }

    private static int findMaxIndex(float[] array) {
        int maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
