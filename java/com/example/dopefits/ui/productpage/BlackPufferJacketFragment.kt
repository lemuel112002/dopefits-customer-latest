package com.example.dopefits.ui.productpage

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.PermissionRequest
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.dopefits.R

class BlackPufferJacketFragment : Fragment() {
    private val CAMERA_PERMISSION_REQUEST_CODE = 1
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Handle any arguments passed to this fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_yellow_hoodie, container, false)
        webView = view.findViewById(R.id.webview)

        checkCameraPermission()

        // Find the back button and set a click listener
        view.findViewById<Button>(R.id.back_button).setOnClickListener {
            findNavController().navigate(R.id.virtualTryOnPageFragment)
        }

        return view
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE)
        } else {
            setupWebView()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupWebView()
            } else {
                // Permission denied, handle appropriately
            }
        }
    }

    private fun setupWebView() {
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.allowFileAccess = true
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                Log.d("WebView", "Page started loading: $url")
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Log.d("WebView", "Page finished loading: $url")
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Log.e("WebView", "Error received: ${error?.description}")
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                Log.d("WebView", "Loading URL: ${request?.url}")
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                handler?.proceed() // Ignore SSL certificate errors for debugging
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(message: ConsoleMessage?): Boolean {
                Log.d("WebView", message?.message() ?: "No message")
                return true
            }

            override fun onPermissionRequest(request: PermissionRequest?) {
                request?.grant(request.resources)
            }
        }

        WebView.setWebContentsDebuggingEnabled(true)
        webView.loadUrl("https://flowcode.com/p/eafQCPeExJ")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up back button action
        view.findViewById<Button>(R.id.back_button).setOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                findNavController().navigateUp() // or finish the fragment
            }
        }

    }

}
