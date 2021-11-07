package provinceGen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author adapted from https://github.com/eliyam32/jump-flooding-voronoi/
 * 
 */
public class JumpFloodingAlgorithm {
	public int numSeedsY = 32; 				// 64 is ok	// 64^2 = 4096
	public int numSeedsX = 32; 				// 64 is ok // 64^2 = 4096	
	public static int imageWidth = 4608; 	// 1024, 512, 256 works	// 5632 - default
	public static int imageHeight = 2816;	// 1024, 512, 256 works	// 2048 - default
	private ArrayList<Point> seeds = new ArrayList<Point>(numSeedsY * numSeedsX); 	
	//private HashMap<Point, Point> closestSeedToPoint = new HashMap<Point, Point>(imageWidth * imageHeight / 4); 	// min. estimate for size
	private HashMap<Point, Point> bufferA;																			// min. estimate for size
	private HashMap<Point, Point> bufferB;																			// min. estimate for size
	private boolean ReadingBufferA = false;
	private ClausewitzMapGenMenu parentMenu; 
	
	public JumpFloodingAlgorithm(int numSeedsX, int numSeedsY, int imageWidth, int imageHeight, ArrayList<Point> seeds,ClausewitzMapGenMenu parentMenu ) {
		this.numSeedsX = numSeedsX;
		this.numSeedsY = numSeedsY;
		this.seeds = seeds; 
		JumpFloodingAlgorithm.imageWidth = imageWidth; 
		JumpFloodingAlgorithm.imageHeight = imageHeight; 
		this.parentMenu = parentMenu; 
		//this.closestSeedToPoint = closestSeedToPoint; 
	}
	
	//Jump Flooding Algorithm
	public HashMap<Point, Point> ExecuteJumpFlooding() {
		// No seeds will just give us a black screen :P
		if(seeds.size() < 1 ) {
			System.out.println( "Please create at least 1 seed.\n" );
			return null;
		}

		System.out.println( "Executing the Jump Flooding algorithm...\n" );

		//// Clear the buffers before we start
		//ClearBuffers();
		bufferA = new HashMap<Point, Point>(imageWidth * imageHeight / 4); 
		bufferB = new HashMap<Point, Point>(imageWidth * imageHeight / 4); 

		//assert( BufferA != NULL && BufferB != NULL );

		//Initialize BufferA with (-1,-1), indicating an invalid closest seed.
		//We don't need to initialize BufferB because it will be written to in the first round.
//		for( int y = 0; y < imageHeight; ++y ) {
//			for( int x = 0; x < imageWidth; ++x ) {
//				bufferA.put(new Point(x, y), new Point(-1, -1)); 
//			}
//		}

		// Put the seeds into the first buffer
		for(int i = 0; i < seeds.size(); ++i ) {
			Point point = seeds.get(i);
			bufferA.put(point, point); 
		}
		
		// Initial step length is half the image's size. If the image isn't square,
		// we use the largest dimension.
		int step = imageWidth > imageHeight ? imageWidth/2 : imageHeight/2;

		// We use this boolean to know which buffer we are reading from
		ReadingBufferA = true;

		// We read from the RBuffer and write into the WBuffer
		HashMap<Point, Point> rBuffer;
		HashMap<Point, Point> wBuffer;
		
		// Carry out the rounds of Jump Flooding
		while( step >= 1 ) {
			System.out.println("Step: " + step);
			// Set which buffers we'll be using
			if( ReadingBufferA == true ) {
				rBuffer = bufferA;
				wBuffer = bufferB;
			}
			else {
				rBuffer = bufferB;
				wBuffer = bufferA;
			}

			// Iterate over each point to find its closest seed
			for( int y = 0; y < imageHeight; ++y ) {
				for( int x = 0; x < imageWidth; ++x ) {
					// The point's absolute index in the buffer
					Point point = new Point(x, y); 

					// The point's current closest seed (if any)
					Point closestSeed = rBuffer.get(point); 

					// Go ahead and write our current closest seed, if any. If we don't do this
					// we might lose this information if we don't update our seed this round.
					wBuffer.put(point, closestSeed); 

					// This is a seed, so skip this point
					if(rBuffer.get(closestSeed) != null && rBuffer.get(closestSeed) == closestSeed) {
						continue; 
					}
					//if(closestSeed.x == x && closestSeed.y == y )
					//	continue;

					// This variable will be used to judge which seed is closest
					float dist;

					if(closestSeed == null) {
						dist = -1; 
					}
//					if(closestSeed.x == -1 || closestSeed.y == -1 )
//						dist = -1; // No closest seed has been found yet
					else
						dist = (closestSeed.x - x) * (closestSeed.x - x) + (closestSeed.y - y) * (closestSeed.y - y); // Current closest seed's distance

					// To find each point's closest seed, we look at its 8 neighbors thusly:
					//   (x-step,y-step) (x,y-step) (x+step,y-step)
					//   (x-step,y     ) (x,y     ) (x+step,y     )
					//   (x-step,y+step) (x,y+step) (x+step,y+step)

					for( int ky = -1; ky <= 1; ++ky ) {
						for( int kx = -1; kx <= 1; ++kx ) {

							// Calculate neighbor's row and column
							int neighbor_y = y + ky * step;
							int neighbor_x = x + kx * step;

							// If the neighbor is outside the bounds of the buffer, skip it
							if( neighbor_x < 0 || neighbor_x >= imageWidth || neighbor_y < 0 || neighbor_y >= imageHeight ) {
								continue;
							} 
							
							// Calculate neighbor's absolute index
							Point nidx = new Point(neighbor_x, neighbor_y); //(neighbor_y * imageWidth ) + neighbor_x ;

							// Retrieve the neighbor's closest point
							Point neighborClosestSeed = rBuffer.get(nidx);

							// If the neighbor doesn't have a closest seed yet, skip it
							if(neighborClosestSeed == null) {
//								System.out.println("neighbor has no closest seed");
								continue; 
							}
//							if( neighborClosestSeed.x == -1 || neighborClosestSeed.y == -1 )
//								continue;
//							System.out.println("neighbor has close seed");
							// Calculate the distance from us to the neighbor's closest seed
							float newDist = (neighborClosestSeed.x - x) * (neighborClosestSeed.x - x) + (neighborClosestSeed.y - y) * (neighborClosestSeed.y - y);

							// If dist is -1, it means we have no closest seed, so we might as well take this one
							// Otherwise, only adopt this new seed if it's closer than our current closest seed
							if( dist == -1 || newDist < dist ) {
								wBuffer.put(point, neighborClosestSeed); //WBuffer[ idx ] = pk;
								dist = newDist;
							}

						}
					}

				}
			}

			// Halve the step.
			step /= (step + 1);

			// Swap the buffers for the next round
			ReadingBufferA = !ReadingBufferA;
			parentMenu.increaseProgress(); 
		}
		
		/**
		 * set results from the correct buffer
		 */
		HashMap<Point, Point> closestSeedToPoint = ReadingBufferA == true ? bufferA : bufferB;
		return closestSeedToPoint; 
	}
}


