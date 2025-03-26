package br.com.useblu.oceands.client.ui.typography

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.ui.caption.CaptionActivity
import br.com.useblu.oceands.client.ui.description.DescriptionActivity
import br.com.useblu.oceands.client.ui.headings.HeadingsActivity
import br.com.useblu.oceands.client.ui.paragraph.ParagraphActivity
import br.com.useblu.oceands.client.ui.subtitles.SubtitlesActivity

class TypographyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_typography)
    }

    fun onClickHeadings(view: View) {
        val headingsIntent = Intent(this, HeadingsActivity::class.java)
        startActivity(headingsIntent)
    }

    fun onClickSubtitles(view: View) {
        val subtitlesIntent = Intent(this, SubtitlesActivity::class.java)
        startActivity(subtitlesIntent)
    }

    fun onClickParagraph(view: View) {
        val paragraphIntent = Intent(this, ParagraphActivity::class.java)
        startActivity(paragraphIntent)
    }

    fun onClickDescription(view: View) {
        val descriptionIntent = Intent(this, DescriptionActivity::class.java)
        startActivity(descriptionIntent)
    }

    fun onClickCaption(view: View) {
        val captionIntent = Intent(this, CaptionActivity::class.java)
        startActivity(captionIntent)
    }
}
