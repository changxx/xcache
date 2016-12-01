<h4>xcache说明：</h4>
spring-cache扩展，支持不同group过期时间不同

<h4>使用说明</h4>
spring配置文件中加入：

    <cache:annotation-driven/>
    <!--cache具体实现，暂时没给默认实现，需要自己实现，具体参考org.springframework.cache.Cache-->
    <bean id="xCacheClient" class="com.netease.kaola.generic.element.cache.xcache.XCacheRedisClient"/>
    <!--spring-cache cacheManager-->
    <bean id="cacheManager" class="com.netease.xcache.XCacheManager">
        <property name="xCache" ref="xCacheClient"/>
    </bean>


<h4>待实现：</h4>
缓存穿透，缓存雪崩预防

<strong>xcache.properties配置说明</strong>
<ul>
    <li>配置文件放在class目录</li>
    <li>xcache.group为固定配置项目，对应Cacheable注解的value</li>
    <li>group.expire为对应分组缓存过期时间</li>
</ul>

<h4>@Cacheable、@CachePut、@CacheEvict 注释介绍</h4>
@Cacheable
<ul>
    <li>@Cacheable 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存</li>
    <li>@Cacheable(value = "accountCache" ,key = "#accountName.concat(#password)") （多参数链接）</li>
    <li>@Cacheable(value = "accountCache", key = "'xxxxxx_' + #id")（一个参数）</li>
    <li>@Cacheable(value = "accountCache", key = "#account.name")（对象属性）
</ul>
@CachePut
<ul>
    <li>@CachePut 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用，同时方法的返回值也被记录到缓存中。</li>
    <li>@CachePut(value = "accountCache", key = "#account.name")（对象属性）</li>
</ul>
@CacheEvict
<ul>
    <li>@CachEvict 主要针对方法配置，能够根据一定的条件对缓存进行清空</li>
    <li>@CachEvict(value = "accountCache", key = "#account.name")（对象属性）</li>
    <li>@Caching(evict = {
                @CacheEvict(value = "accountCache", key = "'account.all_ids'"),
                @CacheEvict(value = "accountCache", key = "'account.id.' + #id"),
                @CacheEvict(value = "accountCache", key = "'account.all_account_email'")
        })（多个同时过期）</li>
</ul>






