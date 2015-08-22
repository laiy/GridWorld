import imagereader.Runner;

final class ImageRunner {

    private ImageRunner() {}

    public static void main(String[] args){
        FuckingImageIO io = new FuckingImageIO();
        ImageProcessor processor = new ImageProcessor();
        Runner.run(io, processor);
    }

}