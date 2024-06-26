package com.rm.android_fundamentals.base.model

import com.rm.android_fundamentals.R
import com.rm.android_fundamentals.base.model.NavConstants.S_COROUTINES
import com.rm.android_fundamentals.base.model.NavConstants.S_COROUTINE_SCOPE
import com.rm.android_fundamentals.base.model.NavConstants.S_ADJUSTABLE_RECYCLER_VIEW
import com.rm.android_fundamentals.base.model.NavConstants.S_API_BOUND_SERVICE
import com.rm.android_fundamentals.base.model.NavConstants.S_BROADCAST_RECEIVER_AIRPLANE_MODE
import com.rm.android_fundamentals.base.model.NavConstants.S_CONTENT_PROVIDER
import com.rm.android_fundamentals.base.model.NavConstants.S_FRAGMENTS_PROGRAMMATICALLY
import com.rm.android_fundamentals.base.model.NavConstants.S_FRAGMENT_XML
import com.rm.android_fundamentals.base.model.NavConstants.S_INTENT_TYPES
import com.rm.android_fundamentals.base.model.NavConstants.S_LAYOUTS
import com.rm.android_fundamentals.base.model.NavConstants.S_LIFECYCLE_COMPONENT
import com.rm.android_fundamentals.base.model.NavConstants.S_LISTENER_WITHOUT_LIFECYCLE_AWARE_COMPONENT
import com.rm.android_fundamentals.base.model.NavConstants.S_LIVEDATA
import com.rm.android_fundamentals.base.model.NavConstants.S_MVC
import com.rm.android_fundamentals.base.model.NavConstants.S_MVP
import com.rm.android_fundamentals.base.model.NavConstants.S_RECYCLER_VIEW
import com.rm.android_fundamentals.base.model.NavConstants.S_REGISTER_ACTIVITY_FOR_RESULT
import com.rm.android_fundamentals.base.model.NavConstants.S_SAVED_INSTANCE_STATE
import com.rm.android_fundamentals.base.model.NavConstants.S_SAVED_STATE_HANDLE
import com.rm.android_fundamentals.base.model.NavConstants.S_BACKGROUND_AND_FOREGROUND_SERVICES
import com.rm.android_fundamentals.base.model.NavConstants.S_ENCRYPTION_DECRYPTION
import com.rm.android_fundamentals.base.model.NavConstants.S_FLOW_BOUND_SERVICE
import com.rm.android_fundamentals.base.model.NavConstants.S_SIMPLE_BOUND_SERVICE
import com.rm.android_fundamentals.base.model.NavConstants.S_STORAGE_TYPES
import com.rm.android_fundamentals.base.model.NavConstants.S_VIEWMODEL_EXAMPLE
import com.rm.android_fundamentals.base.model.NavConstants.S_VIEWPAGER
import com.rm.android_fundamentals.base.model.NavConstants.T_APP_ENTRY_POINTS
import com.rm.android_fundamentals.base.model.NavConstants.T_APP_NAVIGATION
import com.rm.android_fundamentals.base.model.NavConstants.T_ARCHITECTURE
import com.rm.android_fundamentals.base.model.NavConstants.T_ARCHITECTURE_COMPONENTS
import com.rm.android_fundamentals.base.model.NavConstants.T_BROADCAST_RECEIVERS_EXAMPLE
import com.rm.android_fundamentals.base.model.NavConstants.T_COROUTINES
import com.rm.android_fundamentals.base.model.NavConstants.T_KEYSTORE
import com.rm.android_fundamentals.base.model.NavConstants.T_INTENTS
import com.rm.android_fundamentals.base.model.NavConstants.T_SERVICES_EXAMPLE
import com.rm.android_fundamentals.base.model.NavConstants.T_STORAGE
import com.rm.android_fundamentals.base.model.NavConstants.T_UI_COMPONENTS
import com.rm.android_fundamentals.topics.t3_architecturecomponents.s1_savedinstancestate.SavedInstanceStateActivity
import com.rm.android_fundamentals.topics.t3_architecturecomponents.s2_passdatabetweenactivities.ResultActivity
import com.rm.android_fundamentals.topics.t2_appnavigation.s1_fragments.fragmentprogrammatic.FragmentHostManualActivity
import com.rm.android_fundamentals.topics.t2_appnavigation.s1_fragments.fragmentxmlauto.FragmentHostActivity
import com.rm.android_fundamentals.topics.t2_appnavigation.s2_viewpager.HostingActivity
import com.rm.android_fundamentals.topics.t3_architecturecomponents.s3_lifecycle.withlifecyclecomponent.LifecycleActivity
import com.rm.android_fundamentals.topics.t3_architecturecomponents.s3_lifecycle.withoutlifecyclecomponent.WithoutLifeCycleComponentActivity
import com.rm.android_fundamentals.topics.t3_architecturecomponents.s5_viewmodels.ex1.DiceRollActivity
import com.rm.android_fundamentals.topics.t3_architecturecomponents.s5_viewmodels.ex2.SavedStateHandleActivity
import com.rm.android_fundamentals.topics.t3_architecturecomponents.s4_livedata.NameActivity
import com.rm.android_fundamentals.topics.t3_architecturecomponents.s6_coroutinescopes.CoroutineScopesActivity
import com.rm.android_fundamentals.topics.t5_intents.IntentActivity
import com.rm.android_fundamentals.topics.t8_storagetypes.storagetypes.StorageTypesActivity
import com.rm.android_fundamentals.topics.t9_coroutinesflow.CoroutinesActivity
import com.rm.android_fundamentals.topics.t10_architectures.mvc.MVCActivity
import com.rm.android_fundamentals.topics.t10_architectures.mvp.MVPActivity

sealed class NavDest(
    val data: INavDrawerItem
) {

    data object Layouts : NavDest(NavDrawerSection(S_LAYOUTS, R.id.layoutsFragment))
    data object RecyclerViews : NavDest(NavDrawerSection(S_RECYCLER_VIEW, R.id.simpleRvFragment))
    data object DynamicRecyclerView : NavDest(NavDrawerSection(S_ADJUSTABLE_RECYCLER_VIEW, R.id.recyclerViewsFragment))
    data object UIComponents : NavDest(
        NavDrawerTopic(T_UI_COMPONENTS,
            listOf(
                Layouts,
                RecyclerViews,
                DynamicRecyclerView
            )
        )
    )

    data object SavedStateInstance :
        NavDest(NavDrawerSection(S_SAVED_INSTANCE_STATE, targetActivity = SavedInstanceStateActivity::class.java))
    data object RegisterActivityForResult :
        NavDest(NavDrawerSection(S_REGISTER_ACTIVITY_FOR_RESULT, targetActivity = ResultActivity::class.java))
    data object AppEntryPoints : NavDest(
        NavDrawerTopic(
            T_APP_ENTRY_POINTS,
            listOf(
                SavedStateInstance,
                RegisterActivityForResult
            )
        )
    )

    data object FragmentsProgrammatically :
        NavDest(NavDrawerSection(S_FRAGMENTS_PROGRAMMATICALLY, targetActivity = FragmentHostManualActivity::class.java))
    data object FragmentXml :
        NavDest(NavDrawerSection(S_FRAGMENT_XML, targetActivity = FragmentHostActivity::class.java))
    data object ViewPager :
        NavDest(NavDrawerSection(S_VIEWPAGER, targetActivity = HostingActivity::class.java))
    data object AppNavigation : NavDest(
        NavDrawerTopic(
            T_APP_NAVIGATION,
            listOf(
                FragmentsProgrammatically,
                FragmentXml,
                ViewPager
            )
        )
    )

    data object ListenerWithoutLifecycleAwareComponent :
        NavDest(NavDrawerSection(S_LISTENER_WITHOUT_LIFECYCLE_AWARE_COMPONENT, targetActivity =  WithoutLifeCycleComponentActivity::class.java))
    data object LifecycleAwareComponent :
        NavDest(NavDrawerSection(S_LIFECYCLE_COMPONENT, targetActivity = LifecycleActivity::class.java))
    data object ViewModelExample :
        NavDest(NavDrawerSection(S_VIEWMODEL_EXAMPLE, targetActivity = DiceRollActivity::class.java))
    data object SavedStateHandle :
        NavDest(NavDrawerSection(S_SAVED_STATE_HANDLE, targetActivity = SavedStateHandleActivity::class.java))
    data object LiveDataExample :
        NavDest(NavDrawerSection(S_LIVEDATA, targetActivity = NameActivity::class.java))
    data object CoroutineScopes :
        NavDest(NavDrawerSection(S_COROUTINE_SCOPE, targetActivity = CoroutineScopesActivity::class.java))
    data object ArchitectureComponents : NavDest(
        NavDrawerTopic(
            T_ARCHITECTURE_COMPONENTS,
            listOf(
                ListenerWithoutLifecycleAwareComponent,
                LifecycleAwareComponent,
                ViewModelExample,
                SavedStateHandle,
                LiveDataExample,
                CoroutineScopes
            )
        )
    )

    data object IntentTypes : NavDest(NavDrawerSection(S_INTENT_TYPES, targetActivity = IntentActivity::class.java))
    data object IntentExample : NavDest(
        NavDrawerTopic(
            T_INTENTS,
            listOf(
                IntentTypes
            )
        )
    )

    data object BackgroundAndForegroundServices : NavDest(NavDrawerSection(S_BACKGROUND_AND_FOREGROUND_SERVICES, R.id.servicesFragment))
    data object SimpleBoundService : NavDest(NavDrawerSection(S_SIMPLE_BOUND_SERVICE, R.id.simpleBoundServiceFragment))
    data object FlowBoundService : NavDest(NavDrawerSection(S_FLOW_BOUND_SERVICE, R.id.flowBoundServiceFragment))
    data object ApiBoundService : NavDest(NavDrawerSection(S_API_BOUND_SERVICE, R.id.boundServiceFragment))
    data object ServicesExamples : NavDest(
        NavDrawerTopic(
            T_SERVICES_EXAMPLE,
            listOf(
                BackgroundAndForegroundServices,
                SimpleBoundService,
                FlowBoundService,
                ApiBoundService
            )
        )
    )

    data object BroadcastReceiverAirplaneMode : NavDest(NavDrawerSection(
        S_BROADCAST_RECEIVER_AIRPLANE_MODE, R.id.broadcastReceiverFragment))
    data object BroadcastReceiverExample : NavDest(
        NavDrawerTopic(
            T_BROADCAST_RECEIVERS_EXAMPLE,
            listOf(
                BroadcastReceiverAirplaneMode
            )
        )
    )

    data object ContentProviderExample : NavDest(NavDrawerSection(S_CONTENT_PROVIDER, R.id.contentProviderFragment))
    data object StorageTypes : NavDest(NavDrawerSection(S_STORAGE_TYPES, targetActivity = StorageTypesActivity::class.java))
    data object Storages : NavDest(
        NavDrawerTopic(
            T_STORAGE,
            listOf(
                ContentProviderExample,
                StorageTypes
            )
        )
    )


    data object CoroutinesAndFlow : NavDest(NavDrawerSection(S_COROUTINES, targetActivity = CoroutinesActivity::class.java))
    data object CoroutinesExample : NavDest(
        NavDrawerTopic(
            T_COROUTINES,
            listOf(
                CoroutinesAndFlow
            )
        )
    )

    data object ArchitectureMVC : NavDest(NavDrawerSection(S_MVC, targetActivity = MVCActivity::class.java))
    data object ArchitectureMVP : NavDest(NavDrawerSection(S_MVP, targetActivity = MVPActivity::class.java))
    data object ArchitectureTypes : NavDest(
        NavDrawerTopic(
            T_ARCHITECTURE,
            listOf(
                ArchitectureMVC,
                ArchitectureMVP
            )
        )
    )

    data object EncryptionDecryption : NavDest(NavDrawerSection(S_ENCRYPTION_DECRYPTION, R.id.cryptographicFragment))
    data object Crypto : NavDest(
        NavDrawerTopic(
            T_KEYSTORE,
            listOf(
                EncryptionDecryption
            )
        )
    )

    companion object {
        val drawerTopicList = listOf(
            UIComponents,
            AppEntryPoints,
            AppNavigation,
            ArchitectureComponents,
            IntentExample,
            ServicesExamples,
            BroadcastReceiverExample,
            Storages,
            CoroutinesExample,
            ArchitectureTypes,
            Crypto
        )
    }
}