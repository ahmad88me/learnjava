package learnjava;

import javax.swing.JFrame;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.framework.TestList;
import org.jbox2d.testbed.framework.TestbedFrame;
import org.jbox2d.testbed.framework.TestbedModel;
import org.jbox2d.testbed.framework.TestbedPanel;
import org.jbox2d.testbed.framework.TestbedSetting;
import org.jbox2d.testbed.framework.TestbedSetting.SettingType;
import org.jbox2d.testbed.framework.TestbedTest;
import org.jbox2d.testbed.framework.j2d.TestPanelJ2D;



public class Main  extends TestbedTest {

	
	
	public static void main(String[] args) {
		init();
		System.out.println("main");
	}


	@Override
	public void initTest(boolean arg0) {
		setTitle("Couple of Things Test");
	    getWorld().setGravity(new Vec2(0,-10));
	    //new Boxx(1,1,0, 0 );
	    new Boxx(30,1,0,-10,10,BodyType.STATIC);// The ground   
	}
	
	
	//Setup code
	
	class Boxx{
		PolygonShape shape;
		BodyDef bodydef;
		Body body;
		Boxx(float width, float height, float x, float y, int density,BodyType bodytype){
			shape = new PolygonShape();
			shape.setAsBox(width, height);
			bodydef = new BodyDef();
			bodydef.type = bodytype;
			bodydef.position.set(x, y);
			bodydef.allowSleep=false;
			body = getWorld().createBody(bodydef);
			body.createFixture(shape, density);
		}
		Boxx(float width, float height, float x, float y, int density){
			this(width, height,x,y,density,BodyType.DYNAMIC);
		}
		Boxx(float width, float height, float x, float y){
			this(width, height, x,y,1);
		}
		Boxx(float x, float y){
			this(1,1,x,y);
		}
		Boxx(){
			this(0,0);
		}
	}

	//Preparing the testbedhahaha
	public static void init(){
		TestbedModel model = new TestbedModel();         // create our model
		// add tests
		//TestList.populateModel(model);                   // populate the provided testbed tests
		model.addCategory("test");             // add a category
		model.addTest(new Main());                // add our test
		// add our custom setting "My Range Setting", with a default value of 10, between 0 and 20
		model.getSettings().addSetting(new TestbedSetting("test", SettingType.ENGINE, 10, 0, 20));
		TestbedPanel panel = new TestPanelJ2D(model);    // create our testbed panel
		JFrame testbed = new TestbedFrame(model, panel); // put both into our testbed frame
		// etc
		testbed.setVisible(true);
		testbed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public String getTestName() {
		return "Test";
	}

	
}
