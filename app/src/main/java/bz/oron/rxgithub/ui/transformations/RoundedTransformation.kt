package bz.oron.rxgithub.ui.transformations

import android.graphics.*
import com.squareup.picasso.Transformation

/**
 * Created by oron on 1/14/18.
 */
class RoundedTransformation(private val radius: Float? = null, private val margin: Float = 0f) : Transformation {
  override fun transform(source: Bitmap): Bitmap {
    val paint = Paint().apply {
      isAntiAlias = true
      shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    }
    val output = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
    Canvas(output).drawRoundRect(margin, margin, source.width - margin, source.height - margin,
        radius ?: source.width.toFloat() / 2, radius ?: source.height.toFloat() / 2,
        paint)
    if (source != output) {
      source.recycle()
    }
    return output
  }

  override fun key(): String {
    return "rounded(radius=$radius, margin=$margin)"
  }
}