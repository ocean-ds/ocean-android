package br.com.useblu.oceands.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class TypographyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_typography)
    }

    fun onClickHeadings(view: View) {
        val headingsIntent = Intent(TypographyActivity@this, HeadingsActivity::class.java)
        startActivity(headingsIntent)
    }

    fun onClickSubtitles(view: View) {
        val subtitlesIntent = Intent(TypographyActivity@this, SubtitlesActivity::class.java)
        startActivity(subtitlesIntent)
    }

    fun onClickParagraph(view: View) {
        val paragraphIntent = Intent(TypographyActivity@this, ParagraphActivity::class.java)
        startActivity(paragraphIntent)
    }

    fun onClickDescription(view: View) {
        val descriptionIntent = Intent(TypographyActivity@this, DescriptionActivity::class.java)
        startActivity(descriptionIntent)
    }

    fun onClickCaption(view: View) {
        val captionIntent = Intent(TypographyActivity@this, CaptionActivity::class.java)
        startActivity(captionIntent)
    }
}