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
    <li>@CacheEvict(value = "user", key = "#user.id", beforeInvocation = false, condition = "#result.username ne 'zhang'")）（@CacheEvict， beforeInvocation=false表示在方法执行之后调用（#result能拿到返回值了）；且判断condition，如果返回true，则移除缓存；）</li>
</ul>

<h4>提供的SpEL上下文数据：</h4>
<table>
    <tr>
        <th>名字</th>
        <th>位置</th>
        <th>描述</th>
        <th>示例</th>
    </tr>
    <tr>
        <td>methodName</td>
        <td>root对象</td>
        <td>当前被调用的方法名</td>
        <td>#root.methodName</td>
    </tr>
    <tr>
        <td>method</td>
        <td>root对象</td>
        <td>当前被调用的方法名</td>
        <td>#root.method.name</td>
    </tr>
    <tr>
        <td>target</td>
        <td>root对象</td>
        <td>当前被调用的目标对象</td>
        <td>#root.target</td>
    </tr>
    <tr>
        <td>targetClass</td>
        <td>root对象</td>
        <td>当前被调用的目标对象类</td>
        <td>#root.targetClass</td>
    </tr>
    <tr>
        <td>args</td>
        <td>root对象</td>
        <td>当前被调用的方法的参数列表</td>
        <td>#root.args[0]</td>
    </tr>
    <tr>
        <td>caches</td>
        <td>root对象</td>
        <td>当前方法调用使用的缓存列表（如@Cacheable(value={"cache1", "cache2"})），则有两个cache</td>
        <td>#root.caches[0].name</td>
    </tr>
    <tr>
        <td>argument name</td>
        <td>执行上下文</td>
        <td>当前被调用的方法的参数，如findById(Long id)，我们可以通过#id拿到参数</td>
        <td>#user.id</td>
    </tr>
    <tr>
        <td>result</td>
        <td>root对象</td>
        <td>方法执行后的返回值（仅当方法执行之后的判断有效，如‘unless’，'cache evict'的beforeInvocation=false）</td>
        <td>#result</td>
    </tr>
</table>




