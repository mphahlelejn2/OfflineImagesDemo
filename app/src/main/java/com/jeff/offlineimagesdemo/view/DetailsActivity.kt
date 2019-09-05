package com.jeff.offlineimagesdemo.view
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.details_content.*
import com.jeff.offlineimagesdemo.R
import com.jeff.offlineimagesdemo.base.BaseActivity
import com.jeff.offlineimagesdemo.model.Item
import com.jeff.offlineimagesdemo.repo.Repository
import com.jeff.offlineimagesdemo.util.Utils
import com.jeff.offlineimagesdemo.viewModel.ItemDetailsViewModel
import com.jeff.offlineimagesdemo.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.details_content.imageView
import javax.inject.Inject
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import android.net.Uri
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util


class DetailsActivity: BaseActivity() {

    private lateinit var itemDetailsViewModel: ItemDetailsViewModel
    @Inject
    lateinit var repository: Repository
    lateinit var id:String
    lateinit var player:SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_content)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initViewModel()
        getIntentData()
        loadArticleDetails()
        audioPlayerSetup()
        initializePlayButton()
    }

    private fun initializePlayButton() {
        bPlay.setOnClickListener {
            play() }
    }

    private fun play() {
        player.playWhenReady=true
    }

    private fun getIntentData() {
        id = getIntent().getStringExtra("id")
    }

    private fun initViewModel() {
        val viewModelFactory = ViewModelFactory.getInstance(repository)
        itemDetailsViewModel= ViewModelProviders.of(this,viewModelFactory).get(ItemDetailsViewModel::class.java)
    }


    private fun loadArticleDetails() {
        itemDetailsViewModel.loadItemDetails(id)
        itemDetailsViewModel.getItemDetails().observe(this, Observer{
            it?.let{updateUI(it)}
        })
        itemDetailsViewModel.getErrorMsg().observe(this, Observer {
            it?.let {
                showError(resources.getString(R.string.Error_loading_Item_Details))
            }
        })
    }

    private fun updateUI(it: Item) {
        tvTitle.text=it.title
        tvSource.text= it.source_url
        tvID.text=it.imageId
        tvDescription.text = it.description
        Utils.loadImage(this, imageView, it.url)
    }

    private fun audioPlayerSetup(){
        val trackSelector:TrackSelector = DefaultTrackSelector();
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
        player_view.setPlayer(player);
        val dataSourceFactory: DataSource.Factory=  DefaultDataSourceFactory(this, Util.getUserAgent(this, "simpleAudioApp"));
        val audioSourceUri:Uri = Uri.parse("asset:///cat.wav")
        val audioSource:MediaSource = ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(audioSourceUri);
        player.prepare(audioSource);
    }

}