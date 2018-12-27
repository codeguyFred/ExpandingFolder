package cn.codeguy.foldersample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import cn.codeguy.foldersample.video.SampleVideoActivity

class SplashActivity : AppCompatActivity() {
    private val VIEW_KEY = "view_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN->{
               /* val activityOptions=ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this, Pair<View, String>(tv_jump, VIEW_KEY))
                ActivityCompat.startActivity(this,
                        Intent(this, SampleVideoActivity::class.java),
                        activityOptions.toBundle())
*/
                startActivity(Intent(this, SampleVideoActivity::class.java))
                overridePendingTransition(R.anim.right_to_current,R.anim.current_to_left)
            }

            MotionEvent.ACTION_MOVE-> {

            }
            MotionEvent.ACTION_UP->{

            }
        }
        return super.onTouchEvent(event)
    }
}
