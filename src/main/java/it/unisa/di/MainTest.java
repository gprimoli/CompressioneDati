//package it.unisa.di;
//
////import jwave.Transform;
////import jwave.compressions.Compressor;
////import jwave.compressions.CompressorMagnitude;
////import jwave.transforms.AncientEgyptianDecomposition;
////import jwave.transforms.DiscreteFourierTransform;
////import jwave.transforms.FastWaveletTransform;
////import jwave.transforms.wavelets.Wavelet;
////import jwave.transforms.wavelets.WaveletBuilder;
////import jwave.transforms.wavelets.haar.Haar1;
//
//public class MainTest {
//    public static void main(String[] args) {
//
////                        try {
////                    BufferedImage bufferedImage = new BufferedImage(dim.x, dim.y, BufferedImage.TYPE_INT_ARGB_PRE);
////                    for (int x = 0; x < columns.length; x++) {
////                        int[] arr = columns[x].getAllPosition();
////                        for (int y = 0; y < columns[x].size(); y++) {
////                            int rgb = arr[y] << 16 | arr[y] << 8 | arr[y];
////                            bufferedImage.setRGB(x, y, rgb);
////                        }
////                    }
////
////                    File outputfile = new File("src/main/resources/image.jpg");
////
////                    boolean b = ImageIO.write(bufferedImage, "PNG", outputfile);
////                } catch (IOException e) {
////                    throw new RuntimeException(e);
////                }
//
////        Compressor compressor = new CompressorMagnitude(1.0);
////
////        Wavelet[] arrOfWaveletObjects = WaveletBuilder.create2arr();
////        int noOfWavelets = arrOfWaveletObjects.length;
////
////        double[] arrTime = {0, 1, 4, 65};
////        Transform fwt = new Transform(new FastWaveletTransform(new Haar1()));
////
////        double[] arrHilb = fwt.forward(arrTime);
////
////        double[] arrComp = compressor.compress(arrHilb);
////
////        double[] arrReco = fwt.reverse(arrComp);
////        System.out.println();
//    }
//}
//
