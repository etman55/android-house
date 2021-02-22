package com.atef.clubhouse.cloudmessaging

import com.atef.clubhouse.utils.getIMEIDeviceId
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class CloudMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var statusManager: StatusManager

    companion object {
        fun getToken() {
            FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Timber.w(task.exception, "getInstanceId failed")
                        return@OnCompleteListener
                    }
                    // Get new Instance ID token
                    val token = task.result?.token
                    Timber.tag(CloudMessagingService::class.java.simpleName).i("Current token: $token")
                })
        }

        fun getToken(onCompleteListener: OnCompleteListener<InstanceIdResult>) {
            FirebaseInstanceId.getInstance()
                .instanceId
                .addOnCompleteListener(onCompleteListener)
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // log the getting message from firebase
//        Timber.d("From: %s", remoteMessage.from)
        //  if remote message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
//            Timber.d("Message data payload: ${remoteMessage.data}")
            val data = remoteMessage.data
            handleNow(data)
        }
        // if message contains a notification payload.
        remoteMessage.notification?.let {
//            Timber.d("Message Notification Body: %s", remoteMessage.notification!!.body)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sendRegistrationToServer(token)
    }

    /**
     * Persist token on third-party servers using your Retrofit APIs client.
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     *
     * @param token The new token.
     */
    private fun sendRegistrationToServer(token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val deviceId = getIMEIDeviceId(this@CloudMessagingService)
            Timber.tag(CloudMessagingService::class.java.simpleName).i("deviceId $deviceId")
            // todo: add token registration to api
        }
    }

    private fun handleNow(data: Map<String, String>) {
//            Timber.i("type payload: ${data["type"]}")
//        statusManager.updateStatus(Resource.success(CaseStatus.Status1))
    }
}
